drop schema EIMA;

drop table In_out_events; drop table Message_events;
drop table Permission_events; drop table Request_events;
drop table Map_events; drop table Map_objects;
drop table Events; drop table Members;
drop table Users; drop table Incidents;

drop sequence uid_seq; drop sequence incident_id_seq;
drop sequence event_id_seq; drop sequence object_id_seq;
drop sequence incident_member_seq;

create schema EIMA;

create table Incidents (
    incident_id integer unique primary key,
    city varchar(20),
    state varchar(2),
    gps_x decimal,
    gps_y decimal,
    start_time bigint
);
create table Users (
    uid integer unique primary key,
    uname varchar(20) unique,
    token varchar(128),
    current_incident integer,
    password varchar(20),
    organization varchar(20),
    unit varchar(20),
    unit_type varchar(20),
    status varchar(10),
    city varchar(20),
    state varchar(2),
    foreign key (current_incident) references Incidents(incident_id)
);
create table Members (
        incident_member integer primary key,
    uid integer,
    incident_id integer,
    permission integer,
    foreign key (uid) references Users(uid),
    foreign key (incident_id) references Incidents(incident_id)
);
create table Events (
    event_id integer unique primary key,
    time bigint,
    sender integer,
    foreign key (sender) references Members(incident_member)
);
create table Map_objects (
    object_id integer unique primary key,
    client_side_id varchar(20),
    asset_name varchar(20),
    unit varchar(20),
    organization varchar(20),
    status text,
    unit_type varchar(20),
    icon_type varchar(20),
    incident integer,
    incident_member integer default null,
    gps_x decimal,
    gps_y decimal,
    x_array decimal[],
    y_array decimal[],
    radius decimal,
    zone_type varchar(20),
    notes text,
    foreign key (incident) references Incidents(incident_id),
    foreign key (incident_member) references Members(incident_member)
);
create table Map_events (
    event_id int unique primary key,
    object_id int not null,
    gps_x decimal,
    gps_y decimal,
    x_array decimal[],
    y_array decimal[],
    radius decimal,
    notes text,
    status text,
    foreign key (event_id) references Events(event_id),
    foreign key (object_id) references Map_objects(object_id)
);
create table Request_events (
    event_id integer unique primary key,
    permission integer,
    foreign key (event_id) references Events(event_id),
    check (permission in (1, 2, 3))
);
create table Permission_events (
    event_id integer unique primary key,
    recipient integer not null,
    permission integer not null,
    foreign key (event_id) references Events(event_id),
    foreign key (recipient) references Members(incident_member),
    check (permission in (0, 1, 2, 3))
);
create table Message_events (
    event_id integer unique primary key,
    recipient integer,
    message text,
    foreign key (event_id) references Events(event_id),
    foreign key (recipient) references Members(incident_member)
);
create table In_out_events (
    event_id integer unique primary key,
    status varchar(1),
    foreign key (event_id) references Events(event_id),
    check (status in ('I', 'O'))
);

create sequence uid_seq increment by 1 minvalue 0 start with 0 no cycle;
create sequence incident_id_seq increment by 1 minvalue 0 start with 0 no cycle;
create sequence event_id_seq increment by 1 minvalue 0 start with 0 no cycle;
create sequence object_id_seq increment by 1 minvalue 0 start with 0 no cycle;
create sequence incident_member_seq increment by 1 minvalue 0 start with 0 no cycle;