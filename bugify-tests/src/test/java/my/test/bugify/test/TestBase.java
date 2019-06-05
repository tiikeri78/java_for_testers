package my.test.bugify.test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import my.test.bugify.model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class TestBase {

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    private int getIssueId() throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    @BeforeTest
    public boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = new Issue();
            if (issue.withId(getIssueId()).getStatus().equals("resolved")) {
            return true;
        } else return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
