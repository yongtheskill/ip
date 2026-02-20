# Six User Guide

Six is a personal task manager chatbot for tracking todos, deadlines, and events from the command line.

## Quick start

1. Run the jar file.
2. Type commands into the app.
3. Use `bye` to exit.

Tasks are automatically saved to `./data/six.txt`.

## Command format

- Commands are lowercase keywords such as `todo`, `list`, `deadline`.
- Task numbers are **1-based** (e.g. first task is `1`).
- Date/time for `deadline` and `event` must use: `yyyy-MM-dd HHmm`
  - Example: `2026-03-05 1830`

## Features

### Add a todo

Adds a basic task without date/time.

Format: `todo DESCRIPTION`

Example:
`todo submit CS2103 iP`

### Add a deadline

Adds a task that is due at a specific date/time.

Format: `deadline DESCRIPTION /by yyyy-MM-dd HHmm`

Example:
`deadline submit report /by 2026-03-10 2359`

### Add an event

Adds a task with a start and end date/time.

Format: `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

Example:
`event project meeting /from 2026-03-06 1400 /to 2026-03-06 1600`

### List tasks

Shows all tasks in your list.

Format: `list`

### Mark task as done

Marks a task as completed.

Format: `mark TASK_NUMBER`

Example:
`mark 2`

### Unmark task

Marks a completed task as not done.

Format: `unmark TASK_NUMBER`

Example:
`unmark 2`

### Delete task

Removes a task from the list.

Format: `delete TASK_NUMBER`

Example:
`delete 3`

### Find tasks

Finds tasks whose descriptions contain a keyword (case-insensitive).

Format: `find KEYWORD`

Example:
`find report`

### Show reminders

Shows upcoming incomplete deadlines/events within a given number of days.

Formats:

- `remind` (defaults to 7 days)
- `remind DAYS`

Examples:

- `remind`
- `remind 3`

### Exit

Exits the application.

Format: `bye`

## Error handling notes

- Missing required parameters will show an error message explaining what is needed.
- Invalid task numbers (e.g. 0, negative, out of range, or non-integers) are rejected.
- Invalid date/time formats for deadlines/events are rejected.

## Command summary

- `todo DESCRIPTION`
- `deadline DESCRIPTION /by yyyy-MM-dd HHmm`
- `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`
- `list`
- `mark TASK_NUMBER`
- `unmark TASK_NUMBER`
- `delete TASK_NUMBER`
- `find KEYWORD`
- `remind`
- `remind DAYS`
- `bye`
