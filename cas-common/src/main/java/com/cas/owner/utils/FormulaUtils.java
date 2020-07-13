package com.cas.owner.utils;

import com.cloudbees.util.rhino.sandbox.SandboxContextFactory;
import lombok.extern.slf4j.Slf4j;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 公式计算公式
 */
@Slf4j
public class FormulaUtils {
    private FormulaUtils() {}

    /**
     * js 引擎
     */
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public static class SandboxContextSonFactory extends SandboxContextFactory {
        @Override
        protected Context makeContext() {
            return super.makeContext();
        }
    }

    /**
     * 执行 javascript 并获得结果
     * @return
     */
    public static Object executJavascript(String javascript) {
        Object result = null;
        try {
            result = engine.eval(javascript);
        } catch (ScriptException e) {
            log.error("执行JavaScript 出错 {}", result);
        }
        return result;
    }

    /**
     * 利用沙箱对javascript 进行检测，安全
     * @param javascript
     * @return
     */
    public static Object executJavascript2(String javascript) {
        Object result = null;
        SandboxContextSonFactory contextFactory = new SandboxContextSonFactory();
        Context context = contextFactory.makeContext();
        contextFactory.enterContext(context);
        try {
            ScriptableObject prototype = context.initStandardObjects();
            prototype.setParentScope(null);
            Scriptable scope = context.newObject(prototype);
            scope.setPrototype(prototype);
            result = context.evaluateString(scope, javascript, null, -1, null);
        } catch (Exception e) {
            log.error("执行JavaScript出错! javascript={}, error={}", javascript, e);
        } finally {
            Context.exit();
        }
        return result;
    }


    public static void main(String[] args) {
        String str = "2==2";
        System.out.println(executJavascript2(str));
    }

}
