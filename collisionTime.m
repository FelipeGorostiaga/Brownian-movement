function collisionTime(output_file)
    fid = fopen(output_file, 'r');
    N = str2num(fgetl(fid));
    t = [];
    t_int = [];
    ty = 0;

    while !feof(fid) 
        tx = str2num(fgetl(fid));
        t = [t tx];
        t_int = [t_int (tx - ty)];
        ty = tx;

        for i = 1:12
            fgetl(fid);
        end

    end

    fclose(fid);
    
    #frecuencia de colisiones:
    frequency = size(t)(2)/tx

    #promedio de tiempo de colision
    mean = mean(t_int)
    
    hist(t_int, 10)

    x = 0:0.001:sort(t_int)(end);

    #funcion de probabilidad acumulada de los tiempos de colision
    cdf = empirical_cdf(x,t_int);
    #funcion de densidad de probabilidad de los tiempos de colision
    pdf = diff([0 cdf]);

    plot(x, pdf)

        
    
    
end

    