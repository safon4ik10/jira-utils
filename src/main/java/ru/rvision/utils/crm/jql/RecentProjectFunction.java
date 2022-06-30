package ru.rvision.utils.crm.jql;

import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.UserHistoryItem;
import com.atlassian.jira.user.UserProjectHistoryManager;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.JiraDataType;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.jql.query.QueryCreationContext;
import com.atlassian.jira.plugin.jql.function.AbstractJqlFunction;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.query.clause.TerminalClause;
import com.atlassian.query.operand.FunctionOperand;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

@Component
public class RecentProjectFunction extends AbstractJqlFunction {
    private static final Logger log = LoggerFactory.getLogger(RecentProjectFunction.class);

    @ComponentImport
    private final UserProjectHistoryManager userProjectHistoryManager;

    public RecentProjectFunction(UserProjectHistoryManager userProjectHistoryManager) {
        this.userProjectHistoryManager = userProjectHistoryManager;
    }

    @Nonnull
    public MessageSet validate(ApplicationUser searcher, @Nonnull FunctionOperand operand, @Nonnull TerminalClause terminalClause) {
        return validateNumberOfArgs(operand, 0);
    }

    @Nonnull
    public List<QueryLiteral> getValues(QueryCreationContext queryCreationContext, @Nonnull FunctionOperand operand, @Nonnull TerminalClause terminalClause) {
        final List<QueryLiteral> literals = new LinkedList<>();
        final List<UserHistoryItem> projects = userProjectHistoryManager.getProjectHistoryWithoutPermissionChecks(queryCreationContext.getApplicationUser());

        for (final UserHistoryItem userHistoryItem : projects) {
            final String value = userHistoryItem.getEntityId();

            try {
                literals.add(new QueryLiteral(operand, Long.parseLong(value)));
            } catch (NumberFormatException e) {
                log.warn(String.format("User history returned a non numeric project IS '%s'.", value));
            }
        }

        return literals;
    }

    public int getMinimumNumberOfExpectedArguments() {
        return 0;
    }

    @Nonnull
    public JiraDataType getDataType() {
        return JiraDataTypes.PROJECT;
    }
}