<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <propertyAmenity-table
            v-if="propertyAmenitys && propertyAmenitys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:propertyAmenitys="propertyAmenitys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-property-amenitys="getAllPropertyAmenitys"
             >

            </propertyAmenity-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/index";

import PropertyAmenityTable from "@/components/PropertyAmenityTable";
import PropertyAmenityService from "../services/PropertyAmenityService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PropertyAmenityTable,
  },
  data() {
    return {
      propertyAmenitys: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPropertyAmenitys(sortBy='propertyAmenityId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PropertyAmenityService.getAllPropertyAmenitys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.propertyAmenitys.length) {
					this.propertyAmenitys = response.data.propertyAmenitys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching propertyAmenitys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching propertyAmenity details:", error);
      }
    },
  },
  mounted() {
    this.getAllPropertyAmenitys();
  },
  created() {
    this.$root.$on('searchQueryForPropertyAmenitysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPropertyAmenitys();
    })
  }
};
</script>
<style></style>
