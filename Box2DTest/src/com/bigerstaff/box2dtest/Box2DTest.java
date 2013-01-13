package com.bigerstaff.box2dtest;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DTest implements ApplicationListener {
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	World world;
	Box2DDebugRenderer debugRenderer;
	BodyDef bodyDef, groundBodyDef;
	Body body, groundBody;
	CircleShape circle;
	PolygonShape groundBox;
	FixtureDef fixtureDef;
	Fixture fixture;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		world = new World(new Vector2(0, -100), true);
		spriteBatch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		//Create Circle in Box2D world
		//Create a body definition
		bodyDef = new BodyDef();
		//Set our body to dynamic
		bodyDef.type = BodyType.DynamicBody;
		// Set our body's starting position in the world
		bodyDef.position.set(100, 300);
		// Create our body in the world using our body definition
		body = world.createBody(bodyDef);
		// Create a circle shape and set its radius to 6
		circle = new CircleShape();
		circle.setRadius(6f);
		// Create a fixture definition to apply our shape to
		fixtureDef = new FixtureDef();
		fixtureDef.shape = circle;
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f; // Make it bounce a little bit
		// Create our fixture and attach it to the body
		fixture = body.createFixture(fixtureDef);
		
		//Create Ground in Box2D world.
		//Create body definition
		groundBodyDef =new BodyDef();  
		//Set world position
		groundBodyDef.position.set(new Vector2(0, 10));  
		//Create a body from the definition and add it to the world
		groundBody = world.createBody(groundBodyDef);  
		// Create a polygon shape
		groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(camera.viewportWidth, 10.0f);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f); 		
	}

	@Override
	public void dispose() {
		circle.dispose();
		groundBox.dispose();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.end();
		//Box2D Debug Renderer
		debugRenderer.render(world, camera.combined);
		//Step Box2D simulations
		world.step(1/300f, 6, 2);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
