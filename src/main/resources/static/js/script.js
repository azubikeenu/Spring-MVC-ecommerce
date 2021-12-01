$(function() {
	$('#createUser').parsley();
    $('#createCategory').parsley();
    $('#loginForm').parsley();
	$('#updateProfile').parsley();
	$('#createProductForm').parsley();
	$('#productEditForm').parsley();
	
	
	
	//////////------------USERS ---------------////////////
	window.ParsleyValidator.addValidator('checkemail', {
		validateString: function(value) {
			
			const url = `${window.location.origin}/e-shop/api/admin/users/check_email`;
			console.log(url);
			const _csrf = $("input[name= '_csrf']").val()
			params = { email, _csrf }

			return $.ajax({
				url: url,
				method: "POST",
				data: { email: value, _csrf },
				dataType: "json",
				success: function(data) {
					return true;

				}
			});
		}
	});

	// EDIT USERS
	
	$('.editUser').on('click', function(e) {
		e.preventDefault();
	    const {id} = e.target.dataset;
		const url = `${window.location.origin}/e-shop/api/admin/users/${id}`;
		$.get(url,function(data,status){
			$("#firstNameEdit").val(data.firstName)
			$("#lastNameEdit").val(data.lastName);
			$("#emailEdit").val(data.email);
			$("#userId").val(id);
			 (data.roles.includes('ADMIN')) ? $('#rolesAdminEdit').prop("checked" , true) : $('#rolesAdminEdit').prop("checked" , false);
		     (data.roles.includes('USER')) ?  $('#rolesUserEdit').prop("checked" , true) : $('#rolesUserEdit').prop("checked" , false);
              data.active ? $('#isActiveEdit').prop("checked" , true) : $('#isActiveEdit').prop("checked" , false); 	
		})
		
		window.$('#userEditModal').modal;
			 
	})
	 $('#userEditButton').on('click', function(e){
			    e.preventDefault();
				const firstName = $("#firstNameEdit").val()
				const lastName = $("#lastNameEdit").val();
				const email = 	$("#emailEdit").val();
				const id = $("#userId").val()
				const isActive = $('#isActiveEdit').prop("checked") ? true : false;
				const roles = [];
			    if($('#rolesAdminEdit').prop("checked")) roles.push('ADMIN');
				if( $('#rolesUserEdit').prop("checked" )) roles.push('USER');
				let data =  { email,firstName,lastName,active : isActive,roles }
			
				const ORIGIN =window.location.origin;
				const url = `${ORIGIN}/e-shop/api/admin/users/${id}`;
				 fetch(url ,{
					 method : 'PUT',
					 headers : {
					'Content-Type' : 'application/json'
					},
					body : JSON.stringify(data) 
				}).then(response => response.json())
				   .then(data => {
					console.log(data);
					window.location.href =  `${ORIGIN}/e-shop/admin/users/update/redirect`
				})  .catch(err => console.log(err));
						
				
			});
		
		
		
// DELETE USERS 
$('.deleteUser').on('click', function(e){
	 e.preventDefault();
    const r = confirm("Are you sure you want to delete this user ?")
    if(r){
	
     const {id} = e.target.dataset;

  	const ORIGIN =window.location.origin;
	const url = `${ORIGIN}/e-shop/api/admin/users/${id}`;
	fetch(url , {
		method : 'DELETE',
		headers : {
		  'Content-Type' : 'application/json'	
		}		
	}).then(response => response.json())
	   .then(data => {
		console.log(data);
		window.location.href =  `${ORIGIN}/e-shop/admin/users/delete/redirect`
	} ).catch(err => console.log(err))
	
	}else{
		return false;
	}
	
})
		
	//////////------------USERS  END ---------------////////////	
	
	
	
	
    //////////------------CATEGORIES ---------------////////////
	
	// validate unique category
	
	window.ParsleyValidator.addValidator('check_category_name', {
		validateString: function(value) {
			const url = `${window.location.origin}/e-shop/api/admin/categories/check_category_name`;
			console.log(url);
			const _csrf = $("input[name= '_csrf']").val()
			params = { name, _csrf }

			return $.ajax({
				url: url,
				method: "POST",
				data: { name: value, _csrf },
				dataType: "json",
				success: function(data) {
					return true;

				}
			});
		}
	});	
	
	

	// EDIT CATEGORY
	
	$('.editCategory').on('click', function(e) {
		e.preventDefault();
	    const {id} = e.target.dataset;
		const url = `${window.location.origin}/e-shop/api/admin/categories/${id}`;
		$.get(url,function(data,status){
			console.log(data);
			$("#categoryNameEdit").val(data.name)
			$("#categroyDescriptionEdit").val(data.description);
			$("#categoryId").val(id);
              data.enabled ? $('#categoryIsEnabledEdit').prop("checked" , true) : $('#categoryIsEnabledEdit').prop("checked" , false); 	
		})
		
		window.$('#categoryEditModal').modal;

	})
	
	
	 $('#categoryEditButton').on('click', function(e){
			    e.preventDefault();
				const name = $("#categoryNameEdit").val()
				const description = $("#categroyDescriptionEdit").val();
				const id = $("#categoryId").val()
				const enabled = $('#categoryIsEnabledEdit').prop("checked") ? true : false;
			  
				let data =  { name,description,enabled }
			
				const ORIGIN =window.location.origin;
				const url = `${ORIGIN}/e-shop/api/admin/categories/${id}`;
				 fetch(url ,{
					 method : 'PUT',
					 headers : {
					'Content-Type' : 'application/json'
					},
					body : JSON.stringify(data) 
				}).then(response => response.json())
				   .then(data => {
					console.log(data);
					window.location.href =  `${ORIGIN}/e-shop/admin/categories/update/redirect`
				})  .catch(err => console.log(err));
						
				
			});
		

	//DELETE CATEGORY 
	
	$('.deleteCategory').on('click', function(e){
	 e.preventDefault();
    const r = confirm("Are you sure you want to delete this category ?")
    if(r){
	
     const {id} = e.target.dataset;

  	const ORIGIN =window.location.origin;
	const url = `${ORIGIN}/e-shop/api/admin/categories/${id}`;
	fetch(url , {
		method : 'DELETE',
		headers : {
		  'Content-Type' : 'application/json'	
		}		
	}).then(response => response.json())
	   .then(data => {
		console.log(data);
		window.location.href =  `${ORIGIN}/e-shop/admin/categories/delete/redirect`
	} ).catch(err => console.log(err))
	
	}else{
		return false;
	}
	
})

//////////========CATEGORIES END -----------------////////////////

////////-----------PRODUCT START-----------------////////////////
$('#image').on('change', function(e){
	const [file] = this.files
	const fileReader = new FileReader();
	fileReader.onload = function(e){
		$('#thumbnail').attr('src', e.target.result)
	}
	fileReader.readAsDataURL(file);
	
	
})

window.ParsleyValidator.addValidator('check_product_name', {
		validateString: function(value) {
			const url = `${window.location.origin}/e-shop/api/admin/products/check_product_name`;
			const _csrf = $("input[name= '_csrf']").val()
			params = { name, _csrf }

			return $.ajax({
				url: url,
				method: "POST",
				data: { name: value, _csrf },
				dataType: "json",
				success: function(data) {
					return true;

				}
			});
		}
	});	

	//DELETE CATEGORY 
	
	$('.deleteProduct').on('click', function(e){
	 e.preventDefault();
    const r = confirm("Are you sure you want to delete this product ?")
    if(r){
	
     const {id} = e.target.dataset;

  	const ORIGIN =window.location.origin;
	const url = `${ORIGIN}/e-shop/api/admin/products/${id}`;
	fetch(url , {
		method : 'DELETE',
		headers : {
		  'Content-Type' : 'application/json'	
		}		
	}).then(response => response.json())
	   .then(data => {
		console.log(data);
		window.location.href =  `${ORIGIN}/e-shop/admin/products/delete/redirect`
	} ).catch(err => console.log(err))
	
	}else{
		return false;
	}
	
})


				  
})



