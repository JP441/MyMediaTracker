package org.jp441.mymediatracker;

import java.util.ArrayList;

interface UserRepository {
    ArrayList<User> getAllUsers();
    User getUserByName(String username);
    void addUser(String username);
    void removeUser(String username);
}
