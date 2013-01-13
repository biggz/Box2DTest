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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Box2DTest implements ApplicationListener {
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	World world;
	Box2DDebugRenderer debugRenderer;
	static final float WORLD_TO_BOX = 0.01f;
	static final float BOX_TO_WORLD = 100f;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1280, 720);
		world = new World(new Vector2(0, -10), true);
		spriteBatch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void dispose() {
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
