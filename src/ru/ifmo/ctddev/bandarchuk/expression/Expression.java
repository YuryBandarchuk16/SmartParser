package ru.ifmo.ctddev.bandarchuk.expression;

/**
 * Created by YuryBandarchuk on 3/22/17.
 */
public interface Expression<T>  {
    T evaluate(T x);
}
