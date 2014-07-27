package jooby.internal;

import static java.util.Objects.requireNonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jooby.RouteMatcher;
import jooby.RoutePattern;
import jooby.internal.mvc.Routes;

public class RoutePatternImpl implements RoutePattern {

  private static final Pattern GLOB = Pattern
      .compile("\\?|\\*\\*/?|\\*|\\:((?:[^/]+)+?)|\\{((?:\\{[^/]+?\\}|[^/{}]|\\\\[{}])+?)\\}");

  private static final String ANY_DIR = "**";

  private final Function<String, RouteMatcher> matcher;

  private String pattern;

  public RoutePatternImpl(final String verb, final String pattern) {
    requireNonNull(verb, "A HTTP verb is required.");
    requireNonNull(pattern, "A path pattern is required.");
    this.pattern = pattern(verb, Routes.normalize(pattern));
    this.matcher = rewrite(this.pattern);
  }

  @Override
  public String pattern() {
    return pattern;
  }

  @Override
  public RouteMatcher matcher(final String path) {
    requireNonNull(path, "A path is required.");
    return matcher.apply(path);
  }

  private static Function<String, RouteMatcher> rewrite(final String pattern) {
    List<String> vars = new LinkedList<>();
    StringBuilder patternBuilder = new StringBuilder();
    Matcher matcher = GLOB.matcher(pattern);
    int end = 0;
    boolean complex = false;
    while (matcher.find()) {
      patternBuilder.append(quote(pattern, end, matcher.start()));
      String match = matcher.group();
      if ("?".equals(match)) {
        patternBuilder.append("[^/]");
        complex = true;
      } else if ("*".equals(match)) {
        patternBuilder.append("[^/]*");
        complex = true;
      } else if ("**/".equals(match)) {
        patternBuilder.append("(.*/)*");
        complex = true;
      } else if (match.startsWith(":")) {
        complex = true;
        patternBuilder.append("([^/]*)");
        vars.add(match.substring(1));
      } else if (match.startsWith("{") && match.endsWith("}")) {
        complex = true;
        int colonIdx = match.indexOf(':');
        if (colonIdx == -1) {
          patternBuilder.append("([^/]*)");
          vars.add(match.substring(1, match.length() - 1));
        }
        else {
          String regex = match.substring(colonIdx + 1, match.length() - 1);
          patternBuilder.append('(');
          patternBuilder.append(regex);
          patternBuilder.append(')');
          vars.add(match.substring(1, colonIdx));
        }
      }
      end = matcher.end();
    }
    patternBuilder.append(quote(pattern, end, pattern.length()));
    return fn(complex, pattern, complex ? patternBuilder.toString() : pattern, vars);
  }

  private static Function<String, RouteMatcher> fn(final boolean complex, final String rawPattern,
      final String pattern, final List<String> vars) {
    return new Function<String, RouteMatcher>() {
      final Pattern regex = complex ? Pattern.compile(pattern) : null;

      @Override
      public RouteMatcher apply(final String fullpath) {
        String path = fullpath.substring(fullpath.indexOf('/'));
        return complex
            ? new RegexRouteMatcher(path, regex.matcher(fullpath), vars)
            : new SimpleRouteMatcher(path, pattern, fullpath);
      }

      @Override
      public String toString() {
        return rawPattern;
      }
    };
  }

  private static String quote(final String s, final int start, final int end) {
    if (start == end) {
      return "";
    }
    return Pattern.quote(s.substring(start, end));
  }

  private static String pattern(final String verb, final String pattern) {
    StringBuilder buffer = new StringBuilder(verb.toUpperCase());
    if (!pattern.startsWith("/")) {
      buffer.append("/");
    }
    buffer.append(pattern);
    if (pattern.endsWith(ANY_DIR)) {
      buffer.append("/*");
    }
    return buffer.toString();
  }

  @Override
  public String toString() {
    return pattern;
  }
}