package server;

import com.google.gson.annotations.SerializedName;

/**
 * Response represents the server response based on request
 */
class Response {

    enum Status {
        OK,
        ERROR
    }

    @SerializedName("response")
    final Status status;
    final String value;
    final String reason;

    public Response(Status status) {
        this.status = status;
        this.value = null;
        this.reason = null;
    }

    public Response(Status status, String valueOrReason) {
        this.status = status;
        if (status == Status.OK) {
            this.value = valueOrReason;
            this.reason = null;
        } else {
            this.value = null;
            this.reason = valueOrReason;
        }
    }
}
