import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allPersons() {
        return HTTP.get(BASE_URL + "/person", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(user) {
        return HTTP.post(BASE_URL + "/person", user, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(user) {
        return HTTP.patch(BASE_URL + "/person", user, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(user) {
        return HTTP.delete(BASE_URL + "/person", {data: user, headers: authHeader()}).then(
            (response) => {
                return response.data;
            });
    },
};
