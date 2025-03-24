package com.gym.application.pageables;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

public class DefaultPageModel<T> {

    private final Page<T> page;

    /**
     * Creates a new {@link PagedModel} for the given {@link Page}.
     *
     * @param page must not be {@literal null}.
     */
    public DefaultPageModel(Page<T> page) {

        Assert.notNull(page, "Page must not be null");

        this.page = page;
    }

    @JsonProperty
    public List<T> getData() {
        return page.getContent();
    }

    @Nullable
    @JsonProperty("page")
    public DefaultPageModel.PageMetadata getMetadata() {
        return new DefaultPageModel.PageMetadata(page.getSize(), page.getNumber(), page.getTotalElements(),
                page.getTotalPages());
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DefaultPageModel<?> that)) {
            return false;
        }

        return Objects.equals(this.page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page);
    }

    public static record PageMetadata(long size, long number, @JsonProperty("total_elements") long totalElements,
                                      @JsonProperty("total_pages") long totalPages) {

        public PageMetadata {
            Assert.isTrue(size > -1, "Size must not be negative!");
            Assert.isTrue(number > -1, "Number must not be negative!");
            Assert.isTrue(totalElements > -1, "Total elements must not be negative!");
            Assert.isTrue(totalPages > -1, "Total pages must not be negative!");
        }
    }
}
