package org.motechproject.tasks.service.impl;

import org.motechproject.dao.BusinessIdNotUniqueException;
import org.motechproject.tasks.domain.Channel;
import org.motechproject.tasks.domain.Task;
import org.motechproject.tasks.domain.TaskEvent;
import org.motechproject.tasks.ex.ActionNotFoundException;
import org.motechproject.tasks.ex.TriggerNotFoundException;
import org.motechproject.tasks.repository.AllTasks;
import org.motechproject.tasks.service.ChannelService;
import org.motechproject.tasks.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskServiceImpl implements TaskService {
    private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

    private static final int CHANNEL_NAME_IDX = 0;
    private static final int MODULE_NAME_IDX = 1;
    private static final int MODULE_VERSION_IDX = 2;
    private static final int SUBJECT_IDX = 3;

    private AllTasks allTasks;
    private ChannelService channelService;

    @Autowired
    public TaskServiceImpl(AllTasks allTasks, ChannelService channelService) {
        this.allTasks = allTasks;
        this.channelService = channelService;
    }

    @Override
    public void save(final Task task) {
        try {
            allTasks.addOrUpdate(task);
            LOG.info(String.format("Saved task: %s", task.getId()));
        } catch (BusinessIdNotUniqueException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public TaskEvent getActionEventFor(final Task task) throws ActionNotFoundException {
        String[] actionArray = task.getAction().split(":");
        Channel channel = channelService.getChannel(actionArray[CHANNEL_NAME_IDX], actionArray[MODULE_NAME_IDX], actionArray[MODULE_VERSION_IDX]);
        TaskEvent event = null;

        for (TaskEvent action : channel.getActionTaskEvents()) {
            if (action.getSubject().equalsIgnoreCase(actionArray[SUBJECT_IDX])) {
                event = action;
                break;
            }
        }

        if (event == null) {
            throw new ActionNotFoundException(String.format("Cant find action for subject: %s", actionArray[SUBJECT_IDX]));
        }

        return event;
    }

    @Override
    public List<Task> getAllTasks() {
        return allTasks.getAll();
    }

    @Override
    public List<Task> findTasksForTrigger(final String subject) throws TriggerNotFoundException {
        TaskEvent trigger = findTrigger(subject);

        List<Task> tasks = allTasks.getAll();
        List<Task> result = new ArrayList<>(tasks.size());

        for (Task t : tasks) {
            String triggerKey = t.getTrigger().split(":")[SUBJECT_IDX];

            if (triggerKey.equalsIgnoreCase(trigger.getSubject())) {
                result.add(t);
            }
        }

        return result;
    }

    private TaskEvent findTrigger(String subject) throws TriggerNotFoundException {
        List<Channel> channels = channelService.getAllChannels();
        TaskEvent trigger = null;

        for (Channel c : channels) {
            for (TaskEvent t : c.getTriggerTaskEvents()) {
                if (t.getSubject().equalsIgnoreCase(subject)) {
                    trigger = t;
                    break;
                }
            }

            if (trigger != null) {
                break;
            }
        }

        if (trigger == null) {
            throw new TriggerNotFoundException(String.format("Cant find trigger for subject: %s", subject));
        }

        return trigger;
    }

}
