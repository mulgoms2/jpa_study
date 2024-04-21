package com.study.jpa.domain;


import java.time.LocalDateTime;

// 부모 클래스가 빌더패턴을 사용할 때 자식클래스에서 부모 필드를 어떻게 초기화 할까?
public class Children extends Parent {
    private int age;
    private String name;

    private Children(ChildrenBuilder childrenBuilder) {
        super(childrenBuilder);
        this.name = childrenBuilder.name;
        this.age = childrenBuilder.age;
    }

    public static ChildrenBuilder builder() {
        return new ChildrenBuilder();
    }

    public static class ChildrenBuilder extends Parent.ParentBuilder {
        private int age;
        private String name;

        private ChildrenBuilder() {
            super();
        }

        public ChildrenBuilder age(int age) {
            this.age = age;
            return this;
        }

        public ChildrenBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Children build() {
            // 외부 클래스의 생성자로 빌더를 통째로 넘긴다.
            return new Children(this);
        }
    }
}
