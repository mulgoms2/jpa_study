package com.study.jpa.domain;

import java.time.LocalDateTime;

public class Parent {
    private LocalDateTime modifyDate;
    private LocalDateTime createDate;

    protected Parent() {
    }

    private Parent(LocalDateTime modifyDate, LocalDateTime createDate) {
        this.modifyDate = modifyDate;
        this.createDate = createDate;
    }

    public Parent(ParentBuilder builder) {
        this.createDate = builder.createDate;
        this.modifyDate = builder.modifyDate;
    }

    public static ParentBuilder builder() {
        return new ParentBuilder();
    }

    public static class ParentBuilder {
        private LocalDateTime modifyDate;
        private LocalDateTime createDate;

        protected ParentBuilder() {
        }

        public ParentBuilder modifyDate(LocalDateTime modifyDate) {
            this.modifyDate = modifyDate;
            return this;
        }

        public ParentBuilder createDate(LocalDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Parent build() {
            return new Parent(this);
        }
    }
}
