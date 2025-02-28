import {authorizedApi} from "../hooks/withAxiosIntercepted";
import {UserResponse} from "../model/api/UserResponse";
import {UserRequest} from "../model/api/UserRequest";

export class UserApi {
    static login = async (user: UserRequest) =>
        await authorizedApi.post<UserResponse>(`/users/login`, user);
    static create = async (user: UserRequest) =>
        await authorizedApi.post<UserResponse>(`/users/create`,user);
}
