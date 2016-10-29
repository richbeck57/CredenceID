#include <jni.h>
#include <string>

const int ADD  = 1;
const int SUB  = 2;
const int MULT = 3;
const int DIV  = 4;


extern "C" {

jfloat
Java_com_example_rbeck_simplejnicalculator_MainActivity_jniCalc(
        JNIEnv *env,
        jobject,
        jfloatArray arr) {

    jfloat result = 0.0;
    jsize len = env->GetArrayLength(arr);

    if (len != 3) return (float) -99.0;
    jfloat *body = (jfloat *) env->GetFloatArrayElements(arr, 0);
    int calcType = (int) body[0];
    jfloat num1 = body[1];
    jfloat num2 = body[2];

    switch (calcType) {

        case ADD:
            result = num1 + num2;
            break;
        case SUB:
            result = num1 - num2;
            break;
        case MULT:
            result = num1 * num2;
            break;
        case DIV:
            result = num1 / num2;
            break;
    }

    return result;
}

} // extern C