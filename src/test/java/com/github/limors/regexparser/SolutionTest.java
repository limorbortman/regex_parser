package com.github.limors.regexparser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class SolutionTest {

    private Solution underTest = new Solution();

    @Test
    public void example1ReturnTrue() {
        assertTrue(underTest.isMatch("a*", "abc"));
    }

    @Test
    public void example12dReturnTrue() {
        assertTrue(underTest.isMatch("a+", "a"));
    }

    @Test
    public void example3ReturnFalse() {
        assertFalse(underTest.isMatch("*d", "abc"));
    }

    @Test
    public void isStringAndPatternAreEmptyReturnTrue() {
        assertTrue(underTest.isMatch("", ""));
    }

    @Test
    public void isStringIsEmptyAndPatternContainsOnlySpecialCharReturnTrue() {
        assertTrue(underTest.isMatch("+*", ""));
        assertTrue(underTest.isMatch("*+*", ""));
        assertTrue(underTest.isMatch("*****", ""));
        assertTrue(underTest.isMatch("+++", ""));
        assertTrue(underTest.isMatch("+*+*", ""));
    }

    @Test
    public void isStringIsEmptyAndPatternContainsNotOnlySpecialCharReturnFalse() {
        assertFalse(underTest.isMatch("v*", ""));
        assertFalse(underTest.isMatch("*v", ""));
        assertFalse(underTest.isMatch("+v", ""));
        assertFalse(underTest.isMatch("s+", ""));
        assertFalse(underTest.isMatch("s+v", ""));
        assertFalse(underTest.isMatch("s+v", ""));
    }

    @Test
    public void ifPatternIsEmptyButStringIsNotReturnFalse() {
        assertFalse(underTest.isMatch("", "I am not Empty"));
    }

    @Test
    public void ifPatternAndStringHaveTheSameCharReturnTrue() {
        assertTrue(underTest.isMatch("s", "s"));
    }

    @Test
    public void ifPatternAndStringDoseNotHaveTheSameCharReturnTrue() {
        assertFalse(underTest.isMatch("s", "b"));
    }

    @Test
    public void plusWithEmptyStringReturnTrue() {
        assertTrue(underTest.isMatch("+", " "));
    }

    @Test
    public void plusWithStringLength1ReturnTrue() {
        assertTrue(underTest.isMatch("+", "a"));
    }

    @Test
    public void plusWithStringLengthMore1ReturnFalse() {
        assertFalse(underTest.isMatch("+", "av"));
    }

    @Test
    public void plusAtTheEndOfMatchingExactStringReturnTrue() {
        assertTrue(underTest.isMatch("abc+", "abc"));
    }

    @Test
    public void plusAtTheEndOfMatchingStringAndOneMorCharReturnTrue() {
        assertTrue(underTest.isMatch("abc+", "abcd"));
    }

    @Test
    public void plusAtTheEndOfMatchingStringAndMorThenOneMorCharReturnFalse() {
        assertFalse(underTest.isMatch("abc+", "abcdf"));
    }

    @Test
    public void startWithEmptyStringReturnTrue() {
        assertTrue(underTest.isMatch("*", ""));
    }

    @Test
    public void starWithAnyStringReturnTrue() {
        assertTrue(underTest.isMatch("*", "slfjsdofj"));
    }

    @Test
    public void starAtTheEnfOfMatchingStringReturnTrue() {
        assertTrue(underTest.isMatch("abc*", "abcslfjsdofj"));
    }

    @Test
    public void startReturnTrueIfStringAnswerPattern() {
        assertTrue(underTest.isMatch("*a*a*a", "aaa"));
        assertTrue(underTest.isMatch("*a*a*a", "aaaaaaaaaaa"));
        assertTrue(underTest.isMatch("*a*a*a", "abbbbaba"));
    }

    @Test
    public void startReturnFalseIfStringDoseNotAnswerPattern() {
        assertFalse(underTest.isMatch("*a*a*a", "aaab"));
        assertFalse(underTest.isMatch("*a*a*a", "ababab"));
        assertFalse(underTest.isMatch("*a*a*a", "a"));
    }

    @Test
    public void returnFalseIfStringIsShorterThenStarPatternRealLength() {
        assertFalse(underTest.isMatch("*dfg", "gjfhdhdtdtddukgiugidf"));
        assertFalse(underTest.isMatch("*dfg", "df"));
    }

    @Test
    public void returnFalseIfStringIsLongerThenStarPatternRealLength() {
        assertFalse(underTest.isMatch("*dfg", "hhhhhhdfghhhhhhh"));
        assertFalse(underTest.isMatch("*dfg", "dfgfffffff"));
    }

    @Test
    public void returnFalseIfStringIsShorterThenPlusPatternRealLength() {
        assertFalse(underTest.isMatch("+dfg", "df"));
        assertFalse(underTest.isMatch("+dfg", "hdf"));
    }

    @Test
    public void returnFalseIfStringIsLongerThenPlusPatternRealLength() {
        assertFalse(underTest.isMatch("+dfg", "hhdfg"));
    }

    @Test
    public void starMatchMoreThenOneSeq() {
        assertTrue(underTest.isMatch("a*bcd", "abcbcbcbcbcabcd"));
    }

    @Test
    public void plusAndStarShuldeActeAsStar() {
        assertTrue(underTest.isMatch("a*+bcd", "abcd"));
        assertTrue(underTest.isMatch("a*+bcd", "abbcd"));
        assertTrue(underTest.isMatch("a*+bcd", "abbbbbbbbbbbcd"));
        assertTrue(underTest.isMatch("a+*bcd", "abcd"));
        assertTrue(underTest.isMatch("a+*bcd", "abbcd"));
        assertTrue(underTest.isMatch("a+*bcd", "abbbbbbbcd"));
        assertTrue(underTest.isMatch("+*", ""));
        assertTrue(underTest.isMatch("*+", ""));
    }

    @Test
    public void moreThenOnePlus() {
        assertTrue(underTest.isMatch("a+++b", "ab"));
        assertTrue(underTest.isMatch("a+++b", "acb"));
        assertTrue(underTest.isMatch("a+++b", "accb"));
        assertTrue(underTest.isMatch("a+++b", "acccb"));
        assertFalse(underTest.isMatch("a+++b", "accccb"));
    }

}