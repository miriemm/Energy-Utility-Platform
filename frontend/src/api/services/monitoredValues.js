import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    findAllMonitoredValuesBySensor(sensorId) {
        return HTTP.get(BASE_URL + "/monitored-value/" + sensorId, {headers: authHeader()}).then(
            (response) => {
                return response.data;
            }
        );
    }
};
