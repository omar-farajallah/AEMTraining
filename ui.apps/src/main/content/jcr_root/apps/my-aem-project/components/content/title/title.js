use(function() {
	var pageTitle = currentPage.getTitle();
	var state = properties.get("state", "");
	
	return {
		pageTitle: pageTitle,
		state: state
	};
});