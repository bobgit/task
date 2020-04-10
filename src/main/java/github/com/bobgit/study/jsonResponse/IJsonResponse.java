package github.com.bobgit.study.jsonResponse;

public interface IJsonResponse {
    int getStatus();

    void setStatus(int status);

    String getError();

    void setError(String error);
}
