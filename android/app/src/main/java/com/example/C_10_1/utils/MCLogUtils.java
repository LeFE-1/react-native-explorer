package com.example.C_10_1.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by 付晓龙
 * on 2020-02-04
 */
public class MCLogUtils {
    private static String PREFIX = "MCUpdate::";
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static boolean sDebuggable = false;
    private static long sTimestamp = 0;

    public static void setDefaultTag(String tag) {
        PREFIX = tag;
    }

    public static boolean isDebuggable() {
        return sDebuggable;
    }

    public static void setDebuggable(boolean debugable) {
        sDebuggable = debugable;
    }

    public static void v(String tag, String message) {
        if (message == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            String msg = String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), "\n" + message.toString());
            disposeMessage(Log.DEBUG, tag, msg);
        }
    }

    public static void d(String message) {
        if (message == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];

            String msg = String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), "\n" + message.toString());
            disposeMessage(Log.DEBUG, PREFIX, msg);
        }
    }

    public static void d(Object... args) {
        if (args == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            StringBuffer message = new StringBuffer();
            if (args.length > 0) {
                for (int j = 0; j < args.length; j++) {
                    message.append(args[j].toString()).append(" ");
                }
            }
            Log.println(Log.DEBUG, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message.toString()));
        }
    }

    public static void i(String message) {
        if (message == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];

            String msg = String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), "\n" + message.toString());
            disposeMessage(Log.INFO, PREFIX, msg);
        }
    }

    public static void i(Object... args) {
        if (args == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            StringBuffer message = new StringBuffer();
            if (args.length > 0) {
                for (int j = 0; j < args.length; j++) {
                    message.append(args[j].toString()).append(" ");
                }
            }
            Log.println(Log.INFO, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message.toString()));
        }
    }

    public static void w(String message) {
        if (message == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];

            String msg = String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), "\n" + message.toString());
            disposeMessage(Log.WARN, PREFIX, msg);
        }
    }

    public static void w(Object... args) {
        if (args == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            StringBuffer message = new StringBuffer();
            if (args.length > 0) {
                for (int j = 0; j < args.length; j++) {
                    message.append(args[j].toString()).append(" ");
                }
            }
            Log.println(Log.WARN, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message.toString()));
        }
    }

    public static void e(String message) {
        if (message == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];

            String msg = String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), "\n" + message.toString());
            disposeMessage(Log.ERROR, PREFIX, msg);
        }
    }

    public static void e(Object... args) {
        if (args == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            StringBuffer message = new StringBuffer();
            if (args.length > 0) {
                for (int j = 0; j < args.length; j++) {
                    message.append(args[j].toString()).append(" ");
                }
            }
            Log.println(Log.ERROR, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), message.toString()));
        }
    }

    public static void e(Throwable ex) {
        if (ex == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            String logMessage = ex.getMessage();
            String logBody = Log.getStackTraceString(ex);
            String log = String.format(LOG_FORMAT, logMessage, logBody);
            Log.println(Log.ERROR, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), log.toString()));
        }
    }

    public static void e(Throwable ex, Object... args) {
        if (args == null) {
            return;
        }
        if (sDebuggable) {
            final StackTraceElement[] stack = new Throwable().getStackTrace();
            final int i = 1;
            final StackTraceElement ste = stack[i];
            StringBuffer message = new StringBuffer();
            if (args.length > 0) {
                for (int j = 0; j < args.length; j++) {
                    message.append(args[j].toString()).append(" ");
                }
            }
            String log;
            if (ex == null) {
                log = message.toString();
            } else {
                String logMessage = message.toString() == null ? ex.getMessage() : message.toString();
                String logBody = Log.getStackTraceString(ex);
                log = String.format(LOG_FORMAT, logMessage, logBody);
            }
            Log.println(Log.ERROR, PREFIX, String.format("[%s][%s][%s]%s", ste.getFileName(), ste.getMethodName(), ste.getLineNumber(), log.toString()));
        }
    }

    public static void markStart(String msg) {
        sTimestamp = System.currentTimeMillis();
        if (!TextUtils.isEmpty(msg)) {
            e("[Started|" + sTimestamp + "]" + msg);
        }
    }

    public static void elapsed(String msg) {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - sTimestamp;
        sTimestamp = currentTime;
        e("[Elapsed|" + elapsedTime + "]" + msg);
    }


    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }


    private static void disposeMessage(final int priority, final String tag, String message) {
        int segmentSize = 2500;
        long length = message.length();
        if (length <= segmentSize) {
            Log.println(priority, tag, message);
        } else {
            while (message.length() > segmentSize) {
                String logContent = message.substring(0, segmentSize);
                message = message.replace(logContent, "");
                Log.println(priority, tag, "ExceedTheLimit： \n" + logContent);
            }
            Log.println(priority, tag, "ExceedTheLimit： \n" + message);
        }
    }

    public static void i(String tag, String msg) {
        if (sDebuggable) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sDebuggable) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (sDebuggable) {
            Log.e(tag, msg);
        }
    }
}
