package;

import flixel.FlxG;
import flixel.FlxSprite;
import flixel.FlxState;
import flixel.text.FlxText;
import flixel.ui.FlxButton;
import flixel.math.FlxMath;
import flixel.addons.editors.ogmo.FlxOgmoLoader;
import flixel.tile.FlxTilemap;
import flixel.FlxObject;
import flixel.group.FlxGroup;

class PlayState extends FlxState
{
	private var _grpTables:FlxTypedGroup<Table>;
	private var _player:Player;
	private var _map:FlxOgmoLoader;
	private var _mWalls:FlxTilemap;
	
	private function placeEntities(entityName:String, entityData:Xml):Void
	{
		var x:Int = Std.parseInt(entityData.get("x"));
		var y:Int = Std.parseInt(entityData.get("y"));
		if (entityName == "player")
		{
			_player.x = x;
			_player.y = y;
		}
		
		else if (entityName == "table")
		{
			_grpTables.add(new Table(x , y));
		}
	}
	
	override public function create():Void
	{
		_map = new FlxOgmoLoader(AssetPaths.room_001__oel);
		_mWalls = _map.loadTilemap(AssetPaths.set__png, 64, 64, "walls");
		_mWalls.follow();
		_mWalls.setTileProperties(1, FlxObject.ANY);
		_mWalls.setTileProperties(3, FlxObject.ANY);
		_mWalls.setTileProperties(5, FlxObject.ANY);
		_mWalls.setTileProperties(7, FlxObject.ANY);
		_mWalls.setTileProperties(4, FlxObject.NONE);
		add(_mWalls);
		_grpTables = new FlxTypedGroup<Table>();
		add(_grpTables);
		_player = new Player();
		_map.loadEntities(placeEntities, "entities");
		add(_player);
		super.create();
	}

	override public function update(elapsed:Float):Void
	{
		super.update(elapsed);
		FlxG.collide(_player, _mWalls);
	}
}
