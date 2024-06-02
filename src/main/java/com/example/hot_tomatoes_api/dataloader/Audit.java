package com.example.hot_tomatoes_api.dataloader;

import java.util.HashMap;
import java.util.Map;

public class Audit {
    private final int totalLines;
    private int successfulLines;
    private int unreadedLines;
    private Map<String, String> failedLines;

    public Audit(int totalLines) {
        this.totalLines = totalLines;
        this.unreadedLines = totalLines;
        successfulLines = 0;
        failedLines = new HashMap<>();
    }

    public Audit addSuccessfulLine() {
        if (unreadedLines == 0) {
            throw new IllegalStateException("No more lines to read");
        }
        successfulLines++;
        unreadedLines--;
        return this;
    }

    public Audit addFailedLine(String lineContent, String reason) {
        if (unreadedLines == 0) {
            throw new IllegalStateException("No more lines to read");
        }
        failedLines.put(lineContent, reason);
        unreadedLines--;
        return this;
    }

    private String getFailedLines() {
        if (failedLines.isEmpty()) {
            return "";
        }
        StringBuilder failedLinesString = new StringBuilder();
        for (Map.Entry<String, String> failedLine : failedLines.entrySet()) {
            failedLinesString
                    .append("Raw Data: ")
                    .append(failedLine.getKey())
                    .append(" -> Reason: ")
                    .append(failedLine.getValue())
                    .append(System.lineSeparator());
        }
        return failedLinesString.toString();
    }

    @Override
    public String toString() {
        String audit = "Audit {" + System.lineSeparator() +
                "Total lines: " + totalLines + System.lineSeparator() +
                "Successful lines: " + successfulLines + System.lineSeparator() +
                "Unreaded lines: " + unreadedLines + System.lineSeparator() +
                "Failed lines: " + failedLines.size() + System.lineSeparator();
        if (!failedLines.isEmpty()) {
            audit += "{  Log Failed Lines: " + System.lineSeparator() +
                    "  " + getFailedLines() + System.lineSeparator() +
                    "   }" + System.lineSeparator();
        }
        audit += "}";
        return audit;
    }
}
