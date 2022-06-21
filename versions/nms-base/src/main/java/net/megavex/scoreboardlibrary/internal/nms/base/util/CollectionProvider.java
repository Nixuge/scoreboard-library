package net.megavex.scoreboardlibrary.internal.nms.base.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.megavex.scoreboardlibrary.internal.ScoreboardLibraryLogger;

public final class CollectionProvider {
  private static final String FASTUTIL = "it.unimi.dsi.fastutil.objects.";
  private static final MethodHandle mapConstructor, setConstructor, listConstructor;

  static {
    Lookup lookup = MethodHandles.publicLookup();
    var parameters = MethodType.methodType(void.class, int.class);
    mapConstructor = getConstructor(lookup, FASTUTIL + "Object2ObjectOpenHashMap", HashMap.class, parameters);
    setConstructor = getConstructor(lookup, FASTUTIL + "ObjectOpenHashSet", HashSet.class, parameters);
    listConstructor = getConstructor(lookup, FASTUTIL + "ObjectArrayList", ArrayList.class, parameters);
  }

  private CollectionProvider() {
  }

  private static MethodHandle getConstructor(Lookup lookup, String fastUtilClass, Class<?> fallback, MethodType parameters) {
    Class<?> clazz;
    try {
      clazz = Class.forName(fastUtilClass);
      ScoreboardLibraryLogger.logMessage("Using fastutil class " + clazz.getSimpleName());
    } catch (ClassNotFoundException e) {
      clazz = fallback;
    }

    try {
      return lookup.findConstructor(clazz, parameters);
    } catch (NoSuchMethodException | IllegalAccessException e) {
      throw new ExceptionInInitializerError(e);
    }
  }

  public static <K, V> Map<K, V> map(int capacity) {
    try {
      // noinspection unchecked
      return (Map<K, V>) mapConstructor.invokeWithArguments(capacity);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static <E> Set<E> set(int capacity) {
    try {
      // noinspection unchecked
      return (Set<E>) setConstructor.invokeWithArguments(capacity);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

  public static <E> List<E> list(int capacity) {
    try {
      // noinspection unchecked
      return (List<E>) listConstructor.invokeWithArguments(capacity);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }
}
