package hotel.chain.app.roles;

import hotel.chain.app.entities.Schedule;

public class Employee {
    private User user;
    private Schedule schedule;

    public Employee(User user, Schedule schedule){
        this.user = user;
        this.schedule = schedule;
    }

    public Employee() {
        user = new User();
        schedule = new Schedule();
    }

    public User getUser() {
        return user;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString(){
        return
                "user: " + user.toString() +
                "\nschedule : " + schedule.toString();
    }
}
