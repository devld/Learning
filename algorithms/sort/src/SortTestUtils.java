import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class SortTestUtils {

    private static final String METHOD_NAME = "sort";
    private static final Class<?>[] METHOD_ARGS = { int[].class };
    private static final String METHOD_ARGS_LIST = "int[]";

    private static final long TIME_LIMIT = 6 * 1000; // 1 minute

    private static Thread callThread = null;
    private static Thread testThread = null;
    private static long lastRunTime = 0;
    private static boolean lastTestFinished = true;

    private static Method getValidSortMethod(Class<?> clazz) {
        Method method = null;
        try {
            method = clazz.getMethod(METHOD_NAME, METHOD_ARGS);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (method == null || !Modifier.isPublic(method.getModifiers()) || !Modifier.isStatic(method.getModifiers())) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " does not have public static method "
                    + METHOD_NAME + "(" + METHOD_ARGS_LIST + ").");
        }
        return method;
    }

    public synchronized static long runOnce(Class<?> clazz, int[] s) throws TimeoutException {
        final Method method = getValidSortMethod(clazz);
        callThread = Thread.currentThread();
        testThread = new Thread() {
            @Override
            public void run() {
                boolean success = false;
                try {
                    lastRunTime = System.currentTimeMillis();
                    method.invoke(null, s);
                    lastRunTime = System.currentTimeMillis() - lastRunTime;
                    success = true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    if (!success) {
                        lastRunTime = -1;
                    }
                    lastTestFinished = true;
                    callThread.interrupt();
                }
            }
        };
        lastTestFinished = false;
        testThread.start();
        try {
            Thread.sleep(TIME_LIMIT);
        } catch (InterruptedException ignored) {
        }
        if (!lastTestFinished) {
            throw new TimeoutException("Sort timeout after " + TIME_LIMIT + " ms.");
        }
        lastTestFinished = true;
        if (!ArrayUtils.isSorted(s)) {
            throw new RuntimeException("Sort failed, array is not sorted.");
        }
        return lastRunTime;
    }

    public static long runAvarage(Class<?> clazz, int[] s, int times) throws TimeoutException {
        long timeUsage = 0;
        for (int i = 0; i < times; i++) {
            int[] sc = Arrays.copyOf(s, s.length);
            timeUsage += runOnce(clazz, sc);
        }
        return timeUsage / times;
    }

    private static Map<String, Long> runStepTests(Class<?> clazz, int startN, int step, int endN)
            throws TimeoutException {
        Hashtable<String, Long> r = new Hashtable<>();
        for (int i = startN; i <= endN; i *= step) {
            int[] s = ArrayUtils.generateRandomArray(i, 0, i);
            r.put(String.valueOf(i), runAvarage(clazz, s, 10));
        }
        return r;
    }

    public static Map<String, Long> runTest(Class<?> clazz) throws TimeoutException {
        Map<String, Long> r = new Hashtable<>();
        int runTimesEach = 10;
        int n = 1000;
        int maxNum = 1000;
        int[] s = ArrayUtils.generateRandomArray(n, 0, maxNum);
        r.put(n + "(correctness)", runAvarage(clazz, s, runTimesEach));

        maxNum = 10;
        s = ArrayUtils.generateRandomArray(n, 0, maxNum);
        r.put(n + "(0-10)", runAvarage(clazz, s, runTimesEach));

        n = 100000;
        s = ArrayUtils.generateRangeArray(n, 0);
        r.put(n + "(sorted)", runAvarage(clazz, s, runTimesEach));

        ArrayUtils.reverse(s);
        r.put(n + "(reversed)", runAvarage(clazz, s, runTimesEach));

        Map<String, Long> stepResult = runStepTests(clazz, 10, 10, 1000000);

        r.putAll(stepResult);

        return r;
    }

}