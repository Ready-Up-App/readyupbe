package com.readyup.manager;

import com.readyup.domain.Group;
import com.readyup.domain.Person;
import com.readyup.manager.definitions.GroupManager;
import com.readyup.manager.definitions.PersonManager;
import com.readyup.manager.mapper.GroupMapper;
import com.readyup.manager.mapper.PersonMapper;
import com.readyup.ri.entity.GroupEntity;
import com.readyup.ri.entity.PersonEntity;
import com.readyup.ri.repository.GroupRepository;
import com.readyup.ri.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroupManagerImpl implements GroupManager {

    private final GroupRepository groupRepository;
    private final PersonManager personManager;

    public GroupManagerImpl(GroupRepository groupRepository, GroupMapper groupMapper, PersonManager personManager) {
        this.groupRepository = groupRepository;
        this.personManager = personManager;
    }

    @Override
    public boolean create(Group group, String requesterUsername) {
        Person owner = personManager.getPerson(requesterUsername);

        if (Objects.isNull(owner)) {
            return false;
        }

        PersonEntity ownerEntity = PersonMapper.INSTANCE.map(owner);
        GroupEntity newGroup = GroupMapper.INSTANCE.map(group);

        newGroup.addGroupMember(ownerEntity);
        groupRepository.create(newGroup);
        return true;
    }

    @Override
    public Group getGroupFor(Person person) {
//        GroupEntity foundGroup = groupRepository.getGroupFor(personMapper.domainToEntity(person));
//        return groupMapper.entityToDomain(foundGroup);
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.getAllGroups().stream().map(GroupMapper.INSTANCE::map).toList();
    }

    @Override
    public void addMember(String username, String groupUid) {
        Optional<GroupEntity> foundGroupEntity = groupRepository.getGroup(groupUid);

//        groupRepository.addPersonToGroup(groupUid, username);

        if (foundGroupEntity.isPresent()) {
            GroupEntity entity = foundGroupEntity.get();
            PersonEntity personToAdd = PersonMapper.INSTANCE.map(personManager.getPerson(username));
            entity.addGroupMember(personToAdd);
            groupRepository.update(entity);
        }
    }

    @Override
    public boolean update(Group group) {
        return groupRepository.update(GroupMapper.INSTANCE.map(group)) != null;
    }

    @Override
    public boolean delete(Group group) {
        return groupRepository.delete(GroupMapper.INSTANCE.map(group)) == null;
    }

}
