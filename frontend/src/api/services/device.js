import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allDevices() {
        return HTTP.get(BASE_URL + "/device", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    allPersonDevices() {
        return HTTP.get(BASE_URL + "/device/allPersonDevices", {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(device) {
        return HTTP.post(BASE_URL + "/device", device, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(device) {
        return HTTP.patch(BASE_URL + "/device", device, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(device) {
        return HTTP.delete(BASE_URL + "/device", {data: device, headers: authHeader()}).then(
            (response) => {
                return response.data;
            });
    }
};
