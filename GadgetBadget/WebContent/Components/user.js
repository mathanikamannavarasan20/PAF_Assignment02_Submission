/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "UserAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
  location.reload(true);
 onItemSaveComplete(response.responseText, status);

 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("itemid"));
 $("#name").val($(this).closest("tr").find('td:eq(0)').text());
 $("#email").val($(this).closest("tr").find('td:eq(1)').text());
 $("#tel").val($(this).closest("tr").find('td:eq(2)').text());
 $("#uname").val($(this).closest("tr").find('td:eq(3)').text());
 $("#pwd").val($(this).closest("tr").find('td:eq(4)').text());
 
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "UserAPI",
 type : "DELETE",
 data : "ID=" + $(this).data("itemid"),
 dataType : "text",
 complete : function(response, status)
 {

  location.reload(true);
 onItemDeleteComplete(response.responseText, status);

 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
	//cid name email tel uname pwd
// CODE
if ($("#name").val().trim() == "")
 {
 return "Insert name.";
 }
// NAME
if ($("#email").val().trim() == "")
 {
 return "Insert email.";
 } 

// PRICE-------------------------------
if ($("#tel").val().trim() == "")
 {
 return "Insert phone number.";
 }

// DESCRIPTION------------------------
if ($("#uname").val().trim() == "")
 {
 return "Insert  username.";
 }
if ($("#pwd").val().trim() == "")
{
return "Insert password.";
}
return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);

 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 