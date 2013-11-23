package org.nnsoft.trudeau.elo;

/*
 *   Copyright 2013 The Trudeau Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

import static org.nnsoft.trudeau.connector.GraphPopulator.populate;
import static org.nnsoft.trudeau.elo.EloSolver.eloRate;
import static org.nnsoft.trudeau.elo.GameResult.WIN;

import org.junit.Test;
import org.nnsoft.trudeau.api.DirectedGraph;
import org.nnsoft.trudeau.connector.AbstractGraphConnection;
import org.nnsoft.trudeau.inmemory.DirectedMutableGraph;

/**
 * Sample taken from <a href="http://www.teamliquid.net/forum/viewmessage.php?topic_id=253017">teamliquid</a>
 */
public final class EloTestCase
{

    @Test
    public void performElo()
    {
        DirectedGraph<String, GameResult> tournament =
        populate( new DirectedMutableGraph<String, GameResult>() )
        .withConnections( new AbstractGraphConnection<String, GameResult>()
        {

            @Override
            public void connect()
            {
                String zenio = addVertex( "Zenio" );
                String marineking = addVertex( "Marineking" );
                String hongun = addVertex( "Hongun" );
                String nestea = addVertex( "Nestea" );
                String tester = addVertex( "Tester" );
                String nada = addVertex( "Nada" );
                String rainbow = addVertex( "Rainbow" );
                String thewind = addVertex( "Thewind" );
                String inka = addVertex( "Inka" );
                String maka = addVertex( "Maka" );
                String ensnare = addVertex( "Ensnare" );
                String kyrix = addVertex( "Kyrix" );
                String killer = addVertex( "Killer" );
                String slayersboxer = addVertex( "Slayersboxer" );
                String fruitdealer = addVertex( "Fruitdealer" );
                String genius = addVertex( "Genius" );

                // no draws
                addEdge( WIN ).from( zenio ).to( marineking );
                addEdge( WIN ).from( fruitdealer ).to( hongun );
                addEdge( WIN ).from( genius ).to( nestea );
                addEdge( WIN ).from( tester ).to( nada );
                addEdge( WIN ).from( thewind ).to( rainbow );
                addEdge( WIN ).from( maka ).to( inka );
                addEdge( WIN ).from( kyrix ).to( ensnare );
                addEdge( WIN ).from( slayersboxer ).to( killer );
                addEdge( WIN ).from( marineking ).to( fruitdealer );
                addEdge( WIN ).from( tester ).to( genius );
                addEdge( WIN ).from( thewind ).to( maka );
                addEdge( WIN ).from( kyrix ).to( slayersboxer );
                addEdge( WIN ).from( marineking ).to( tester );
                addEdge( WIN ).from( kyrix ).to( thewind );
                addEdge( WIN ).from( kyrix ).to( marineking );
            }

        } );

        PlayersRank<String> playersRank = new SimplePlayersRank();

        eloRate( tournament ).wherePlayersAreRankedIn( playersRank ).withKFactor( 80 );

        // TODO find a way to assert the rate worked
        System.out.println( playersRank );
    }

}
