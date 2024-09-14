import http from "../http-common";

class PropertyAmenityService {
  getAllPropertyAmenitys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/propertyAmenity/propertyAmenitys`, searchDTO);
  }

  get(propertyAmenityId) {
    return this.getRequest(`/propertyAmenity/${propertyAmenityId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/propertyAmenity?field=${matchData}`, null);
  }

  addPropertyAmenity(data) {
    return http.post("/propertyAmenity/addPropertyAmenity", data);
  }

  update(data) {
  	return http.post("/propertyAmenity/updatePropertyAmenity", data);
  }
  
  uploadImage(data,propertyAmenityId) {
  	return http.postForm("/propertyAmenity/uploadImage/"+propertyAmenityId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new PropertyAmenityService();
