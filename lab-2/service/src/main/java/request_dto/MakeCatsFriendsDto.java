package request_dto;

public class MakeCatsFriendsDto {
    private long catId1;
    private long catId2;

    public MakeCatsFriendsDto(long catId1, long catId2) {
        this.catId1 = catId1;
        this.catId2 = catId2;
    }

    public long getCatId1() {
        return this.catId1;
    }

    public long getCatId2() {
        return this.catId2;
    }

    public void setCatId1(long catId1) {
        this.catId1 = catId1;
    }

    public void setCatId2(long catId2) {
        this.catId2 = catId2;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MakeCatsFriendsDto)) return false;
        final MakeCatsFriendsDto other = (MakeCatsFriendsDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCatId1() != other.getCatId1()) return false;
        if (this.getCatId2() != other.getCatId2()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MakeCatsFriendsDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $catId1 = this.getCatId1();
        result = result * PRIME + (int) ($catId1 >>> 32 ^ $catId1);
        final long $catId2 = this.getCatId2();
        result = result * PRIME + (int) ($catId2 >>> 32 ^ $catId2);
        return result;
    }

    public String toString() {
        return "MakeCatsFriends(catId1=" + this.getCatId1() + ", catId2=" + this.getCatId2() + ")";
    }
}
