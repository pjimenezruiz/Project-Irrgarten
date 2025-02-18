#encoding utf-8

require_relative '../Directions'
require_relative '../Dice'
require_relative '../GameCharacter'
require_relative '../Orientation'
require_relative '../Shield'
require_relative '../Weapon'
require_relative '../GameState'
require_relative '../Labyrinth'
require_relative '../Game'
require_relative '../Monster'
require_relative '../Player'
require_relative '../UI/textUI'
require_relative '../controller/controller'

class TestP3
    def main
        nplayers = 1
        game = Irrgarten::Game.new(nplayers)
        view = UI::TextUI.new
	controller = Control::Controller.new(game,view)
	controller.play()
    end
end

test = TestP3.new
test.main
