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

import static org.nnsoft.trudeau.utils.Assertions.checkNotNull;

import org.nnsoft.trudeau.api.DirectedGraph;

/**
 * TODO Fill me!!
 *  
 * @param <P> The player's type
 */
public final class DefaultRankingSelector<P>
    implements RankingSelector<P>
{

    private final DirectedGraph<P, GameResult> tournamentGraph;

    /**
     * Creates a new instance of {@link DefaultRankingSelector} for gived graph.
     * @param tournamentGraph the graph
     */
    public DefaultRankingSelector( DirectedGraph<P, GameResult> tournamentGraph )
    {
        this.tournamentGraph = tournamentGraph;
    }

    /**
     * {@inheritDoc}
     */
    public KFactorBuilder<P> wherePlayersAreRankedIn( PlayersRank<P> playersRank )
    {
        playersRank = checkNotNull( playersRank, "ELO ranking can not be applied if players can not be ranked!" );
        return new DefaultKFactorBuilder<P>( tournamentGraph, playersRank );
    }

}
