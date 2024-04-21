package com.study.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Minwoo extends CommonDate {
    @Id
    private Long id;
    private String name;
    private Integer age;

    protected Minwoo() {
    }

    private Minwoo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    protected Minwoo(MinwooBuilder<?, ?> builder) {
        super(builder);
        this.age = builder.age;
        this.name = builder.name;
    }

    public static MinwooBuilder<?, ?> builder() {
        return new MinwooBuilderImpl();
    }

    public static abstract class MinwooBuilder<C extends Minwoo, B extends MinwooBuilder<C, B>> extends CommonDateBuilder<C, B> {
        private String name;
        private Integer age;

        public B name(String name) {
            this.name = name;
            return self();
        }

        public B age(Integer age) {
            this.age = age;
            return self();
        }

        public abstract C build();

        @Override
        protected abstract B self();
        // 왜 롬복 빌더는 내부클래스에 toString이 구현되어있을까?
    }

    private static final class MinwooBuilderImpl extends MinwooBuilder<Minwoo, MinwooBuilderImpl> {
        private MinwooBuilderImpl() {
        }

        @Override
        protected MinwooBuilderImpl self() {
            return this;
        }

        public Minwoo build() {
            return new Minwoo(this);
        }
    }

}