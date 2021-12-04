$(function() {
	$('#createUser').parsley();
    $('#createCategory').parsley();
    $('#loginForm').parsley();
	$('#updateProfile').parsley();
	$('#createProductForm').parsley();
	$('#productEditForm').parsley();
	$('#checkOut').parsley();
	
	
	
	
	
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

  const price = parseFloat($("#productPrice").text());
//MODIFY PRICE ON QUANITY CHANGE
  $('#productQuantity').change(function(e){
	  const quantity=   parseInt(e.target.value);
      const newPrice = price*quantity ;
    $ ("#productPrice").text(`${newPrice}`) ;  
	
})


//////////////////----------------SHOPPING CART ----------------------------//////////////////////
// ADD ITEM TO SHOPPING Cart

$('#addToCart').click(function(e){
	const productId =  $('#productId').val();
	const productQuantity = $('#productQuantity').val()
	const imagePath =window.location.origin +"/e-shop" +$("#productImage").val();
	const totalQuantity = $('#totalQuantity').val();
	const productName = $("#productName").text();
	const price =  $ ("#productPrice").text();
    const product = {name : productName , quantity :productQuantity, image :  imagePath , id : productId ,totalQuantity,price }
    storeCartItemsInLocalStorage(product);
})

function storeCartItemsInLocalStorage ( item ) {
    const cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );
    if(cart.length > 0 ){
      const foundItem =cart.find(el => el.id === item.id);
     foundItem ? foundItem.quantity = item.quantity : cart.push(item)
	 }else{
		cart.push( item );
	}  	 
    localStorage.setItem( "cart", JSON.stringify( cart ) )
    window.location.href  = `${window.location.origin}/e-shop/cart`
}
   
   const cartBody=  document.getElementById('cartBody')
    if(cartBody){
	  cartBody.innerHTML = "";
      let html = "";
	  const cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );   
      if(cart.length > 0){
            for(const item of cart){
					html +=	
					`
                    <div class="row pt-3 item-hl">
                        <div class="col-md-2">
							<img src="${item.image}" alt="" class="img-fluid align-self-end"
                                width="100px" height="100px">
						</div>
                        <div class="col-md-3 "> <strong>${item.name}</strong>
                        </div>
                        <div class="col-md-2"><span id="itemPrice">&#163;${item.price}</span> &times; <span id="itemQuantity">${item.quantity}</span> </div>
                       <div class="col-md-2">
                            <div>
                                <ul class="pagination">
                                    <li class="page-item   ">
                                        <a class="page-link minusQuantity"  href="#">-</a>
                                    </li>
                                    <li class="page-item active">
                                        <input type="text" name="" id="" onkeydown="return false"
                                            class="form-control text-center" data-product_id=${item.id} data-parsley-min="0" data-parsley-max="${item.totalQuantity}" data-parsley-trigger="focusout"  value="${item.quantity}" disabled  style="width:70px;">
                                    </li>
                                    <li class="page-item">
                                        <a class="page-link plusQuantity " data-max= "${item.totalQuantity}"href="#">+</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-md-2 text-center"><i data-product_id=${item.id} class="fa fa-trash deleteItem" style="cursor:hand"></i></div>
					  </div>	
					`
				}
				
			
		 		const cartSummary = document.getElementById('cartSummary');
			    const {subTotal, totalItems} = getTotalQuantityAndPriceOfItems();
			    const cartSummaryContent = 
						`
					  <div class="row ">
                        <div class="col action-item text-center ">
							<h3>Subtotal :<span>&#163;</span><span id="subTotal">${subTotal}</span></h3>
                            <p class="lead">Total Items : <span id="totalItems"> ${totalItems}</span> item(s)<p>                       
                        </div>
                    </div>
                    <div class="row ">
                        <div class="col action-item " style="border-top: none;">
                            <button class="btn btn-primary btn-block" id="checkOutBtn"> PROCEED TO CHECKOUT</button>
                        </div>
                    </div>
					`
			        cartSummary.insertAdjacentHTML('afterbegin',cartSummaryContent);
	
 			}else{
	            
	            html+=`<div class="alert  alert-info"> 							
  					<strong>NO ITEMS IN CART</strong>
				</div>`
			}
	    	cartBody.insertAdjacentHTML('afterbegin',html);
		
	}
	
	// UPDATE CART ITEMS 
	
	const plusQuantities = document.querySelectorAll('.plusQuantity')
	
     plusQuantities.forEach(e => e.addEventListener('click',function decrement(e){	
		e.preventDefault();
            const container  = e.target.parentElement.previousElementSibling;
			const maxQuantity = e.target.dataset.max;
			console.log(maxQuantity);
            const textbox = container.querySelector('input[type="text"]');
            let {value} = textbox;
			const {product_id} = textbox.dataset;
            value = parseInt(value) +1 ;
            textbox.value = value <= maxQuantity ? value : maxQuantity;	
			persistQuantityInLocalStorage(product_id,value)
			reflectQuantityOnDiv(container ,value);
			reflectSubTotalAndTotalItems();
			
	}) )	
	
	
	const minusQuantities = document.querySelectorAll('.minusQuantity')	
	minusQuantities.forEach(e => e.addEventListener('click',function decrement(e){	
		e.preventDefault();
            const container  = e.target.parentElement.nextElementSibling
            const textbox = container.querySelector('input[type="text"]');
            let {value} = textbox;
			const {product_id} = textbox.dataset;
            value = parseInt(value) -1 ;
			if(value>=1){
				textbox.value = value;
			    persistQuantityInLocalStorage(product_id,value)
				reflectQuantityOnDiv(container ,value);
				reflectSubTotalAndTotalItems();
			}
           
	}) )
	
	
	function persistQuantityInLocalStorage(product_id,value){
			const cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );   
		    const foundItem =cart.find(el => el.id === product_id);
			foundItem.quantity = value
			localStorage.setItem("cart",JSON.stringify( cart ) );
	}
	
	function reflectQuantityOnDiv(container ,value){
		const quantityContainer =container.parentElement.parentElement.parentElement.previousElementSibling;
		const quantitySpan = quantityContainer.querySelector('span#itemQuantity')
		quantitySpan.innerText = value;
	}
	
	// DELETE CART ITEMS 
	const deleteButtons = document.querySelectorAll('.deleteItem');
    deleteButtons.forEach(e => e.addEventListener('click',function(e){
		const {product_id} = e.target.dataset;
		let cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );   
		 cart = cart.filter(el => el.id !== product_id);
		e.target.parentElement.parentElement.remove();
		localStorage.setItem("cart",JSON.stringify( cart ) );
		reflectSubTotalAndTotalItems();
	
	}) )
	
   function getTotalQuantityAndPriceOfItems(){
	let cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );
	 const  subTotal  = cart.reduce((acc,current) => acc + (current.price * current.quantity),0);
	 const totalItems = cart.length ;
     return {subTotal , totalItems}
	
	}
	
	function reflectSubTotalAndTotalItems(){
		const {subTotal, totalItems} = getTotalQuantityAndPriceOfItems();
		const subTotalContainer = document.getElementById('subTotal');
		const totalItemsContainer = document.getElementById('totalItems');
		subTotalContainer.innerText = subTotal.toFixed(2);
		totalItemsContainer.innerText = totalItems;
	}
	
	


$('#checkOutBtn').click(function(e){
	    e.preventDefault();
		const cart = !localStorage.getItem( "cart" ) ? [] : JSON.parse( localStorage.getItem( "cart" ) );  
		if(cart.length > 0){
			$.ajaxSetup({contentType : "application/json"});
			payload = cart;
			const url =`${window.location.origin}/e-shop/api/cart/save_items`
			$.post(url,JSON.stringify(payload))
			.done(function(response){
				if(response['operationResult']=== "SUCCESS"){
					window.location.href = `${window.location.origin}/e-shop/checkout`;
					localStorage.setItem("cart",JSON.stringify( [] ) );
				}else{
					console.log('An Error Occured')
				}
			})

		}
			
	})
	
	
		
})




