import React, {useState} from 'react';

export function ProjectCard({project}) {

    const [toggle, setToggle] = useState(false);

    return (
        <div className={"project-card"}>
            <h2>{project.projectName}</h2>
            <div onClick={() => setToggle(!toggle)}>
                {!toggle ? project.members.length > 1
                        ? <p>There are {project.members.length} members</p>
                        : <p>There is {project.members.length} member</p>
                    : project.members.map((member, index) =>
                        <p key={index}>{member.realUserName}</p>
                    )
                }
            </div>
        </div>
    )
}