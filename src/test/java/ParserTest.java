import commands.*;
import exceptions.InvalidParameterException;
import exceptions.SixException;
import exceptions.UnknownCommandException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_byeCommand_returnsByeCommand() throws SixException {
        Command result = Parser.parse("bye");
        assertTrue(result instanceof ByeCommand);
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws SixException {
        Command result = Parser.parse("list");
        assertTrue(result instanceof ListCommand);
    }

    @Test
    public void parse_nullCommand_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse(null));
    }

    @Test
    public void parse_emptyCommand_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_whitespaceOnlyCommand_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("   "));
    }

    @Test
    public void parse_unknownCommand_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> Parser.parse("unknown"));
    }

    @Test
    public void parse_validMarkCommand_returnsMarkCommand() throws SixException {
        Command result = Parser.parse("mark 1");
        assertTrue(result instanceof MarkCommand);
    }

    @Test
    public void parse_markCommandWithoutIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_markCommandWithSpaceButNoIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("mark "));
    }

    @Test
    public void parse_markCommandWithInvalidIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void parse_validUnmarkCommand_returnsUnmarkCommand() throws SixException {
        Command result = Parser.parse("unmark 2");
        assertTrue(result instanceof UnmarkCommand);
    }

    @Test
    public void parse_unmarkCommandWithoutIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parse_unmarkCommandWithInvalidIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("unmark xyz"));
    }

    @Test
    public void parse_validTodoCommand_returnsTodoCommand() throws SixException {
        Command result = Parser.parse("todo read book");
        assertTrue(result instanceof TodoCommand);
    }

    @Test
    public void parse_todoCommandWithoutDescription_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_todoCommandWithSpaceButNoDescription_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("todo "));
    }

    @Test
    public void parse_validDeadlineCommand_returnsDeadlineCommand() throws SixException {
        Command result = Parser.parse("deadline return book /by 2024-12-25 1800");
        assertTrue(result instanceof DeadlineCommand);
    }

    @Test
    public void parse_deadlineCommandWithoutBy_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void parse_deadlineCommandWithoutDescription_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void parse_deadlineCommandEmptyDescription_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("deadline /by 2024-12-25 1800"));
    }

    @Test
    public void parse_deadlineCommandEmptyByTime_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("deadline return book /by "));
    }

    @Test
    public void parse_validEventCommand_returnsEventCommand() throws SixException {
        Command result = Parser.parse("event meeting /from 2024-12-25 1400 /to 2024-12-25 1600");
        assertTrue(result instanceof EventCommand);
    }

    @Test
    public void parse_eventCommandWithoutFromTo_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("event meeting"));
    }

    @Test
    public void parse_eventCommandWithoutDescription_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("event"));
    }

    @Test
    public void parse_eventCommandMissingTo_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("event meeting /from 2024-12-25 1400"));
    }

    @Test
    public void parse_validDeleteCommand_returnsDeleteCommand() throws SixException {
        Command result = Parser.parse("delete 1");
        assertTrue(result instanceof DeleteCommand);
    }

    @Test
    public void parse_deleteCommandWithoutIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parse_deleteCommandWithInvalidIndex_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Parser.parse("delete abc"));
    }

    @Test
    public void parse_commandWithLeadingTrailingSpaces_parsesCorrectly() throws SixException {
        Command result = Parser.parse("  list  ");
        assertTrue(result instanceof ListCommand);
    }

    @Test
    public void parse_markCommandWithExtraSpaces_parsesCorrectly() throws SixException {
        Command result = Parser.parse("mark    5");
        assertTrue(result instanceof MarkCommand);
    }
}
