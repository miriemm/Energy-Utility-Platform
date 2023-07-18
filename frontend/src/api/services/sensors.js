import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allSensors() {
        return HTTP.get(BASE_URL + "/sensor", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    allPersonSensors() {
        return HTTP.get(BASE_URL + "/sensor/allPersonSensors", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(sensor) {
        return HTTP.post(BASE_URL + "/sensor", sensor, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(sensor) {
        return HTTP.patch(BASE_URL + "/sensor", sensor, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(sensor) {
        return HTTP.delete(BASE_URL + "/sensor", {data: sensor, headers: authHeader()}).then(
            (response) => {
                return response.data;
            });
    }
};
