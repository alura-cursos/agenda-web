var deleta_aluno = function(id){
	$.ajax({
	    url: '/api/aluno/' + id,
	    type: 'DELETE',
	    success: function(result) {
	    	location.reload();
	    }
	});
}