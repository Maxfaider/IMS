package io.amecodelabs.ims.view.userstage;

import io.amecodelabs.ims.models.utils.ContentValues;
import io.amecodelabs.ims.service.UserService;

public class PresenterUsersViewImpl implements PresenterUsersView<ContentValues> {
	private UsersView<ContentValues> usersView;
	
	public PresenterUsersViewImpl(UsersView<ContentValues> usersView) {
		this.usersView = usersView;
	}

	@Override
	public void addUser(ContentValues user) {
		this.usersView.showLoadProgress();
		UserService.createUser(user, 
			(response, document) -> {
				this.usersView.hiddenloadProgress();
				if(response.getValueInteger("ok") == 1) {
					this.usersView.showMessage("Success", "User has been successfully added");
					this.usersView.update(document);
					this.usersView.clearForm();
				} else {
					this.usersView.showMessage("Error", "User could not be added");
				}	
			}, 
			(err) -> {
				this.usersView.hiddenloadProgress();
				this.usersView.showMessage("Error", "Connect failed or interrupted");
			}
		);
	}
	
	@Override
	public void getUsers() {
		this.usersView.showLoadProgress();
		UserService.getUsers(
			(response) -> {
				this.usersView.hiddenloadProgress();
				if(response.getValueInteger("ok") == 1) {
					this.usersView.loadUsers(response.getArrayContentValues("response"));
				} else {
					this.usersView.showMessage("Error", "Users have not been downloaded");
				}	
			}, 
			(err) -> {
				this.usersView.hiddenloadProgress();
				this.usersView.showMessage("Error", "Connect failed or interrupted");
			}
		);
	}

	@Override
	public void editUser(ContentValues user) {
		this.usersView.showLoadProgress();
		UserService.editUsers(user, 
			(response) -> {
				this.usersView.hiddenloadProgress();
				if(response.getValueInteger("ok") == 1) {
					this.usersView.showMessage("Update", "User has been updated");
				} else {
					this.usersView.showMessage("Update", "User has been updated");
				}
			}, 
			(err) -> {
				this.usersView.hiddenloadProgress();
				this.usersView.showMessage("Error", "Connect failed or interrupted");
			}
		);
	}
	
	@Override
	public void deleteUser(String id) {
		this.usersView.showLoadProgress();
		UserService.deleteUsers(id, 
			(response) -> {
				this.usersView.hiddenloadProgress();
				if(response.getValueInteger("ok") == 1) {
					this.usersView.showMessage("Update", "User has been removed");
					this.usersView.remove(id);
				} else {
					this.usersView.showMessage("Error", "User has not been removed");
				}
			}, 
			(err) -> {
				this.usersView.hiddenloadProgress();
				this.usersView.showMessage("Error", "Connect failed or interrupted");
			}
		);
	}

	@Override
	public UsersView<ContentValues> getView() {
		return usersView;
	}

}
