package ru.fedotov.dto.cats.request_models;

import jakarta.validation.constraints.Positive;

public class MakeCatsFriendsRequest {
    @Positive(message = "Id must be positive!")
    private Long catId1;
    @Positive(message = "Id must be positive!")
    private Long catId2;

    public MakeCatsFriendsRequest() {
    }


    public @Positive(message = "Id must be positive!") Long getCatId1() {
        return this.catId1;
    }

    public @Positive(message = "Id must be positive!") Long getCatId2() {
        return this.catId2;
    }

    public void setCatId1(@Positive(message = "Id must be positive!") Long catId1) {
        this.catId1 = catId1;
    }

    public void setCatId2(@Positive(message = "Id must be positive!") Long catId2) {
        this.catId2 = catId2;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MakeCatsFriendsRequest)) return false;
        final MakeCatsFriendsRequest other = (MakeCatsFriendsRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$catId1 = this.getCatId1();
        final Object other$catId1 = other.getCatId1();
        if (this$catId1 == null ? other$catId1 != null : !this$catId1.equals(other$catId1)) return false;
        final Object this$catId2 = this.getCatId2();
        final Object other$catId2 = other.getCatId2();
        if (this$catId2 == null ? other$catId2 != null : !this$catId2.equals(other$catId2)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MakeCatsFriendsRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $catId1 = this.getCatId1();
        result = result * PRIME + ($catId1 == null ? 43 : $catId1.hashCode());
        final Object $catId2 = this.getCatId2();
        result = result * PRIME + ($catId2 == null ? 43 : $catId2.hashCode());
        return result;
    }

    public String toString() {
        return "MakeCatsFriendsRequest(catId1=" + this.getCatId1() + ", catId2=" + this.getCatId2() + ")";
    }
}
