import io.cucumber.plugin.event.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseFinished;

public class TestListener implements EventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {
        System.out.println("Test case finished: " + event.getTestCase().getName());
        System.out.println("Test case status: " + event.getResult().getStatus());
    }
}
