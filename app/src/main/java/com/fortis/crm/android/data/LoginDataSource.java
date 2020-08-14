package com.fortis.crm.android.data;

import com.fortis.crm.android.data.model.LoggedInUser;
import com.fortis.crm.android.repository.Result;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(username, "管理员");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error("Error logging in");
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}