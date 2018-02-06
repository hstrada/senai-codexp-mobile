<?php
//Autoload
$loader = require 'vendor/autoload.php';

//Instanciando objeto
$app = new \Slim\Slim(array(
    'templates.path' => 'templates'
));

//Listando todas
$app->get('/jogos/', function() use ($app){
	(new \controllers\Jogo($app))->lista();
});

//get jogo
$app->get('/jogos/:id', function($id) use ($app){
	(new \controllers\Jogo($app))->get($id);
});

//nova jogo
$app->post('/jogos/', function() use ($app){
	(new \controllers\Jogo($app))->nova();
});

//edita jogo
$app->put('/jogos/:id', function($id) use ($app){
	(new \controllers\Jogo($app))->editar($id);
});

//apaga jogo
$app->delete('/jogos/:id', function($id) use ($app){
	(new \controllers\Jogo($app))->excluir($id);
});

//Rodando aplicação
$app->run();