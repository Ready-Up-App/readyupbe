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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class GroupManagerImpl implements GroupManager {

    private final GroupRepository groupRepository;
    private final PersonManager personManager;
    private final GroupMapper groupMapper;

    public GroupManagerImpl(GroupRepository groupRepository, GroupMapper groupMapper, PersonManager personManager) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.personManager = personManager;
    }

    @Override
    public boolean create(Group group, String requesterUsername) {
        PersonEntity owner = personManager.mapper().domainToEntity(personManager.getPerson(requesterUsername));
        if (Objects.isNull(owner)) {
            return false;
        }
        GroupEntity newGroup = groupMapper.domainToEntity(group);
        newGroup.addGroupMember(owner);
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
        return groupRepository.getAllGroups().stream().map(groupMapper::entityToDomain).toList();
    }

    @Override
    public void addMember(String username, String groupUid) {
        Optional<GroupEntity> foundGroupEntity = groupRepository.getGroup(groupUid);


        if (foundGroupEntity.isPresent()) {
            GroupEntity entity = foundGroupEntity.get();
            PersonEntity personToAdd = personManager.mapper()
                    .domainToEntity(personManager.getPerson(username));
            entity.addGroupMember(personToAdd);
            groupRepository.update(entity);
        }
    }

    @Override
    public boolean update(Group group) {
        return groupRepository.update(groupMapper.domainToEntity(group)) != null;
    }

    @Override
    public boolean delete(Group group) {
        return groupRepository.delete(groupMapper.domainToEntity(group)) == null;
    }

}
