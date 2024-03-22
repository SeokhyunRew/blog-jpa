package com.example.blogjpa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JUnitTest {
    @DisplayName("1 + 2는 3이다")  // 테스트 이름
    @Test
    public void test() {
        int a = 1;
        int b = 2;
        int sum = 3;

        Assertions.assertEquals(sum, a + b);  // 값이 같은지 검증
        assertThat(a + b).isEqualTo(sum);
    }
}