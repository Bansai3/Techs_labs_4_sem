package ru.fedotov.dto.cats.request_models;

import jakarta.validation.constraints.Positive;

public class RemoveCatRequest {
    @Positive(message = "Id must be positive!")
    private Long catId;

    public RemoveCatRequest() {
    }


    public @Positive(message = "Id must be positive!") Long getCatId() {
        return this.catId;
    }

    public void setCatId(@Positive(message = "Id must be positive!") Long catId) {
        this.catId = catId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RemoveCatRequest)) return false;
        final RemoveCatRequest other = (RemoveCatRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$catId = this.getCatId();
        final Object other$catId = other.getCatId();
        if (this$catId == null ? other$catId != null : !this$catId.equals(other$catId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RemoveCatRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $catId = this.getCatId();
        result = result * PRIME + ($catId == null ? 43 : $catId.hashCode());
        return result;
    }

    public String toString() {
        return "RemoveCatRequest(catId=" + this.getCatId() + ")";
    }
}
