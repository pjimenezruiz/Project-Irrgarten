#encoding:utf-8

require_relative 'FuzzyPlayer' #PREGUNTAR

module Irrgarten
	
	class Game
		private
			@@MAX_ROUNDS=10
			@@N_ROWS=5
			@@N_COLS=5
			@@E_ROW=4
			@@E_COL=4
			
			def next_player()
				@current_player_index = (@current_player_index + 1)%@players.length()
			end
			
			def actual_direction(d)
				cr = @players[@current_player_index].get_row()
				cc = @players[@current_player_index].get_col()
				
				prefdirections = @labyrinth.valid_moves(cr,cc)
				output = @players[@current_player_index].move(d,prefdirections)
				
				return output
			end
			
			def combat(m)
				r = 0
				w = Irrgarten::GameCharacter::PLAYER
				
				pattack = @players[@current_player_index].attack();
				lose = m.defend(pattack)
				
				while (lose == false) and (r < @@MAX_ROUNDS)
					w = Irrgarten::GameCharacter::MONSTER
					r += 1
					
					mattack = m.attack()
					lose = @players[@current_player_index].defend(mattack)
					
					if lose == false
						pattack = @players[@current_player_index].attack();
						w = Irrgarten::GameCharacter::PLAYER
						lose = m.defend(pattack)
					end
				end
				
				log_rounds(r,@@MAX_ROUNDS)
				
				return w
			end
			
			def manage_reward(w)
				if (w == Irrgarten::GameCharacter::PLAYER)
					@players[@current_player_index].receive_reward()
					log_player_won()
				else
					log_monster_won()
				end
					
			end
			
			def manage_resurrection()
				resurrect = Irrgarten::Dice.resurrect_player()
				
				if resurrect == true
					@players[@current_player_index].resurrect()
					fp = FuzzyPlayer.new(@players[@current_player_index])
					@players[@current_player_index]=fp
					@labyrinth.put_fuzzy_player(fp)
					log_resurrected()
				else
					log_player_skip_turn()
				end
			end
			
			def log_player_won()
				@log = @log +"Player won \n"
			end
			
			def log_monster_won()
				@log = @log +"Monster won \n"
			end
			
			def log_resurrected()
				@log = @log +"Player resurrected \n"
			end
			
			def log_player_skip_turn()
				@log = @log +"Player lost turn \n"
			end
			
			def log_player_no_orders()
				@log = @log +"Player couldn't follow the orders \n"
			end
			
			def log_no_monsters()
				@log = @log +"Player moved to a empty square or couldn't move \n"
			end
			
			def log_rounds(r,m)
				@log = @log+r.to_s+"/"+m.to_s+ "combat rounds \n"
			end
			
		public
			def initialize(np) 
				@players = []
				@monsters = []
				@log = ""
				
				i = 0
				while i < np
					@players[i] = Irrgarten::Player.new(i.to_s,Irrgarten::Dice.random_intelligence,Irrgarten::Dice.random_strength)
					i+=1
				end
				
				@current_player_index = Irrgarten::Dice.who_starts(np)
				@labyrinth = Irrgarten::Labyrinth.new(@@N_ROWS,@@N_COLS,@@E_ROW,@@E_COL)
				
				@labyrinth.spread_players(@players)
				configure_labyrinth()
			end
			
			def finished()
				return @labyrinth.have_a_winner()
			end
			
			def next_step(d)
				@log = ""
				dead = @players[@current_player_index].dead()
				if dead == false
					direct = actual_direction(d)
					if direct != d
						log_player_no_orders()
					end
					
					monst = @labyrinth.put_player(direct,@players[@current_player_index])
					
					if monst == nil
						log_no_monsters()
					else
						winner = combat(monst)
						manage_reward(winner)
					end
				else
					manage_resurrection()
				end
				
				endgame = finished()
				
				if endgame == false
					next_player()
				end
				
				return endgame
			end
			
			def get_gamestate()
				p = ""
				i = 0
				while i < @players.length
					p = p + @players[i].to_s + " "
					i+=1
				end
				
				m = ""
				i = 0
				while i < @monsters.length
					m = m + @monsters[i].to_s + " "
					i+=1
				end
				
				gamestate = Irrgarten::GameState.new(@labyrinth.to_s,p,m,@current_player_index,@labyrinth.have_a_winner,@log)
				return gamestate
			end
			
			def configure_labyrinth()
				ns = @@N_ROWS * @@N_COLS
				
				i = 0 
				while i < ns / 5
					r = Dice.random_pos(@@N_ROWS)
					c = Dice.random_pos(@@N_COLS)
					mname ="monster"+i.to_s
					@monsters[i] = Monster.new(mname,Irrgarten::Dice.random_intelligence,Irrgarten::Dice.random_strength)
					@labyrinth.add_monster(r,c,@monsters[i])
					i+=1
				end
				
				i = 0 
				while i < (ns / 20)
					r = Dice.random_pos(@@N_ROWS)
					c = Dice.random_pos(@@N_COLS)
					@labyrinth.add_block(Irrgarten::Orientation::HORIZONTAL,r,c,@@N_ROWS)
						
					r = Dice.random_pos(@@N_ROWS)
					c = Dice.random_pos(@@N_COLS)
					@labyrinth.add_block(Irrgarten::Orientation::VERTICAL,r,c,@@N_COLS)
					i+=1
				end
			end
	end
end
	
