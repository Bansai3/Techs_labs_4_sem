package request_dto;

public class RemoveOwnerDto {
    private long ownerId;

    public RemoveOwnerDto(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RemoveOwnerDto)) return false;
        final RemoveOwnerDto other = (RemoveOwnerDto) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getOwnerId() != other.getOwnerId()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RemoveOwnerDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $ownerId = this.getOwnerId();
        result = result * PRIME + (int) ($ownerId >>> 32 ^ $ownerId);
        return result;
    }

    public String toString() {
        return "RemoveOwner(ownerId=" + this.getOwnerId() + ")";
    }
}
